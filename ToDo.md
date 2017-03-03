1. analizis model 2
2. AlagutSzajLeptet szekvencia -> utkozik() ki lett véve de így nem is jó szerintem, terepasztalnak kéne szólni hogy ütközés történt (Peti)
3. Jatszik szekvenciát átalakítottam (Peti)
4. initTerepasztal szekvenciát hozzáadtam, de még nem valósítottam meg (Peti)
5. init szekvenciához hozzáadtam a initTerepasztal szekvenciát

Class diagram:
terepasztal új változó: utkozes: Boolean -> ha terepasztalon ütközés történt akkor true // doksi nincs updatelve 

Szekvenciák:
Start ok
init ok kép updatelve // doskiban lecserélve
input ok
Jatszik ok kép updatelve // doksiban lecserélve
VonatMozgat léptet fv szerelvényt kapjon doksiban
UtkozesVizsgalat nem ok -> át kell alakítani -> kiesett
ValtoClick módosítva -> elég egy paint munka SetAktualisAg -> valt
AlagutSzajClick ok
ValtoValt módosítva
AlagutSzajLeptet ok // módosítva
AllomasLeptet ok // módosítva
ValtoLeptet nem ok -> kb ugyanaz a baj mint az AlagutSzajLeptetnel
KocsiLeszallit ok // módosítva
Kirajzol ok
initTerepasztal -> szekvenciát már hozzáadtam a uml fájlba de még nem csináltam meg

leptet fvek átírása -> úgy vettük, hogy mindig előtte hívódik meg, de nem
beSin szekvenciát kéne csinálni
