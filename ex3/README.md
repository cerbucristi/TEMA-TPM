# Exercițiul 3

Problema abordării propuse apare la existența a două sau mai multe thread-uri fie consumatoare, fie producătoare.

Să considerăm următorul scenariu la pornirea execuției programului:
1.  Coada este vidă;
2.  Primul thread consumator intră în metoda `deq()` și ajunge până în blocul `try` unde rămâne blocat la apelul `empty.await()`. Lacătul asociat este eliberat, iar thread-ul curent așteaptă un semnal;
3.  Al doilea thread, tot consumator, preia lacătul și se blochează în același apel `empty.await()`;
4.  Două sau mai multe thread-uri producător inserează în coadă și unul din ele apelează `empty.signal()`;
5.  Un thread consumator primește semnalul și continuă execuția din `deq()`;
6.  Când acest thread își termină execuția, este posibil ca în coadă să mai existe elemente. Pentru că variabila `count` nu mai ajunge niciodată la valoarea 1, niciun thread nu mai apelează `empty.signal()`, astfel că al doilea nostru thread consumator rămâne blocat așteptând semnal.

Pentru a rezolva această problemă de **livelock**, ar trebui să modificăm transmiterea semnalelor prin apelarea metodei `signalAll()`, pentru a da semnal tuturor thread-urilor blocate de `await` (ele vor trebui să reobțină ulterior accesul la lacăt pentru a accesa secțiunea critică).

Cele două funcții ar trebui să transmită semnalele astfel:

```
public T enq(T x, int id) {
  ...
  if (count == 1) {
    empty.signalAll();
  }
  ...
}
```
```
public T deq(int id) {
  ...
  if (count == items.length - 1) {
    full.signalAll();
  }
  ...
}
```