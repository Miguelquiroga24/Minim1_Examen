# Mínim 1 - Gestió d'avions, vols i maletes facturades

## Estat actual del projecte

## Part 1 – Component Java 

La primera part del projecte s'ha desenvolupat correctament. Inclou:

- ✔️ Definició de les **classes** `Avion`, `Vuelo` i `Maleta`, amb tots els atributs i mètodes necessaris.
- ✔️ Implementació de la **interfície `Manager`** amb les operacions demanades (afegir/modificar avió, afegir/modificar vol, facturar maleta, obtenir maletes).
- ✔️ Implementació de la **façana `ManagerImpl`** utilitzant el **patró Singleton** i estructures de dades adients (`List`, `Stack`).
- ✔️ Totes les operacions tenen **traces amb LOG4J**, amb nivell `INFO`, i missatges d’error amb `ERROR`.
- ✔️ S'ha creat una classe de **tests amb JUnit** (`ManagerImplTest.java`) amb diversos casos per verificar el correcte funcionament.

---

## Part 2 – Servei REST i Swagger (en desenvolupament 🛠️)

La segona part del projecte ha estat iniciada però actualment presenta alguns problemes tècnics:

- ✔️ S'ha implementat correctament el **servei REST** (`Service.java`) utilitzant Swagger, amb operacions.
  - Tot i que el servidor s’inicia correctament i es genera el fitxer `swagger.json`, **la interfície gràfica de Swagger (Swagger UI)** no es mostra en accedir a `http://localhost:8080/swagger`.
  - En lloc de veure la interfície interactiva, només es mostra el contingut de que no existeix.
  - Aquest problema pot estar relacionat amb la **manca de fitxers front-end de Swagger UI** dins del projecte, o la seva configuració incompleta.
  - També hi ha dificultats per fer les peticions REST directament des d’una interfície gràfica com Swagger, que és un requisit per a validar dues operacions.
