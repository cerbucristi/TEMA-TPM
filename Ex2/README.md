# Exercițiul 2

## Motivul principal:
Un lacat este obtinut de un thread prin apelarea metodei `lock()` doar daca este disponibil, altfel firul de executie este blocat pana cand lockul este eliberat.

In utilizarea unui lock se prefera ca apelul `lock()` sa fie executat inainte de blocul try pentru ca: daca e cazul ca o exceptie de tip unchecked din `lock()` sa fie aruncata, ea poate sa nu conduca la apelul incorect al lui unlock(). Totusi, daca folosim lock() inainte de blocul try, atunci in `finally` putem apela `unlock()`, in cazul al doilea va trebui sa verificam daca lock ul este obtinut inainte sa facem `unlock()` pentru a nu primi exceptia de tipul `IllegalMonitorStateException`.

Prima varianta este preferata in practica, dupa cum e folosit si in documentatiile oficiale si in acest caz tratarea exceptiilor aruncate de metoda `lock()` pot fi tratate intr-un `try` separat.

Totodata, in documentatia java a clasei `ReentrantLock`, apelul catre `lock()` este lasat  în afara blocului `try`.

## Concluzie
Apelul metodei se face in afara blocului `try` deoarece aparitia unei exceptii în `lock()` duce la apelarea incorecta a metodei `unlock()`.

#### Bune practici de asemenea:
În general, o buna practica este de a pastra blocurile `try` cat mai simple si granulare, iar tratarea posibilelor exceptii aruncate, checked sau unchecked sa se faca corespunzator.

#### Să luăm exemplul practic 'gresit' din tema:

Să presupunem că implementarea `someLock`` este similară cu implementarea clasei `ReentrantLock` din java unde documentația oficială oferă cel puțin un caz când o excepție neverificată este aruncată în timpul execuției, după cum urmează:

`This lock supports a maximum of 2147483647 recursive locks by the same thread. Attempts to exceed this limit result in Error throws from locking methods.`

Acum să analizăm exemplul nostru pe baza informațiilor cunoscute (`lock` in blocul `try`):

`lock()` în sine poate arunca excepții atunci când numărul de blocări reintrante depășește limita.
```
try {
   someLock.lock();// aruncă o excepție, lock() a eșuat
   .....
}
finally {
   someLock.unlock();// apelare nepotrivită, aruncă o excepție <- deoarece această operațiune va arunca din nou în blocul finally, poate duce la o excepție nedorită și netratată în continuare
}
```


#### Mai multe informatii suplimentare din documentatie:

```
void lock()
Acquires the lock.
If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired.

Implementation Considerations

A Lock implementation may be able to detect erroneous use of the lock, such as an invocation that would cause deadlock,
and may throw an (unchecked) exception in such circumstances. The circumstances and the exception type must be documented by that Lock implementation.

Java ReentrantLock class documentation says: 
It is recommended practice to always immediately follow a call to lock with a try block, most typically in a before/after construction such as:

  
 class X {
   private final ReentrantLock lock = new ReentrantLock();
   // ...

   public void m() {
     lock.lock();  // block until condition holds
     try {
       // ... method body
     } finally {
       lock.unlock()
     }
   }
 }
``````