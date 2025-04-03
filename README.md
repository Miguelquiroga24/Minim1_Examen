# Mínim 1 - Gestió d'avions, vols i maletes facturades
## Part 1 – Component Java 

La primera part del projecte s'ha desenvolupat correctament. Inclou:

-  Definició de les **classes** `Avion`, `Vuelo` i `Maleta`, amb tots els atributs i mètodes necessaris.
-  Implementació de la **interfície `Manager`** amb les operacions demanades (afegir/modificar avió, afegir/modificar vol, facturar maleta, obtenir maletes).
-  Implementació de la **façana `ManagerImpl`** utilitzant el **patró Singleton** i estructures de dades adients (`List`, `Stack`).
-  Totes les operacions tenen **traces amb LOG4J**, amb nivell `INFO`, i missatges d’error amb `ERROR`.
-  S'ha creat una classe de **tests amb JUnit** (`ManagerImplTest.java`) amb diversos casos per verificar el correcte funcionament.

## Part 2 – Servei REST i Swagger (en desenvolupament...)

La segona part del projecte ha estat iniciada però actualment presenta alguns problemes tècnics:

- No s'ha acabat de implementar correctament el **servei REST** (`Service.java`).
  - Tot i que el servidor s’inicia correctament, **"la interfície gràfica de Swagger"** no es mostra en accedir a `http://localhost:8080/swagger`.
  - En lloc de veure la interfície interactiva, només es mostra el contingut de que no existeix
