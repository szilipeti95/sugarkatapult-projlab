1. analizis model 2
2. AlagutSzajLeptet szekvencia -> utkozik() ki lett véve de így nem is jó szerintem, terepasztalnak kéne szólni hogy ütközés történt (Peti)
3. Jatszik szekvenciát átalakítottam (Peti)
4. initTerepasztal szekvenciát hozzáadtam, de még nem valósítottam meg (Peti)
5. init szekvenciához hozzáadtam a initTerepasztal szekvenciát

Class diagram:
terepasztal új változó: utkozes: Boolean -> ha terepasztalon ütközés történt akkor true // doksi nincs updatelve 
mozdony új függvény: utkozesVizsgal(): Boolean -> végig nézi, hogy másik vonattal ütközött-e a vonat, true ha igen // doksi nincs updatelve
			(szerintem utkozik() fv-re lehet nincs szükség)

Szekvenciák:
Start ok
init ok kép updatelve // doskiban lecserélve
input ok
Jatszik ok kép updatelve // doksiban lecserélve
VonatMozgat ok
UtkozesVizsgalat nem ok -> át kell alakítani
ValtoClick ok
AlagutSzajClick ok
ValtoValt ok
AlagutSzajLeptet nem ok -> lsd. feljebb
AllomasLeptet módosítottam de amm ok
ValtoLeptet nem ok -> kb ugyanaz a baj mint az AlagutSzajLeptetnel
KocsiLeszallit ok
Kirajzol ok
initTerepasztal -> szekvenciát már hozzáadtam a uml fájlba de még nem csináltam meg