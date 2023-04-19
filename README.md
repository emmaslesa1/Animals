# Animals
Ovaj program omogućava zoološkom vrtu praćenje životinja.
Ovaj sistem sadrži klasu Životinja koja ima svoj id koji predstavlja šifru pod kojom se životinja vodi u bazi podataka, te ime koji predstavlja ime životinje.
U slučaju da se konstruktoru ili setteru proslijedi prazno ime ili id treba baciti IllegalArgumentException sa tekstom "Ime ne može biti prazno" odnosno "Id ne može biti prazan".
Klase Pas i Mačka su naslijeđene iz klase Životinja.
Klase Vuk, DomaćiPas su naslijeđeni iz klase Pas, dok su klase Lav, Tigar, DomaćaMačka naslijeđene iz klase Mačka.
