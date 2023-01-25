JFLAGS = -encoding UTF-8 -implicit:none -g
JC = javac

JVM = java
JVMFLAGS =

### REGLES ESSENTIELLES ###

Main.class : Main.java Menu.class
    ${JC} ${JCFLAGS} Main.java

Menu.class : Menu.java BoutonMenu.class ImageDeFond.class
    ${JC} ${JCFLAGS} Menu.java

BoutonMenu.class : BoutonMenu.java Samegame.class
    ${JC} ${JCFLAGS} BoutonMenu.java

Samegame.class : Samegame.java Grille.class Score.class
    ${JC} ${JCFLAGS} Samegame.java

Grille.class : Grille.java MenuFin.class
    ${JC} ${JCFLAGS} Grille.java

GestionnaireSouris.class : GestionnaireSouris.java Grille.class Score.class
    ${JC} ${JCFLAGS} GestionnaireSouris.java

MenuFin.class : MenuFin.java BoutonMenuFin.class
    ${JC} ${JCFLAGS} MenuFin.java

BoutonMenuFin.class : BoutonMenuFin.java
    ${JC} ${JCFLAGS} BoutonMenuFin.java

ImageDeFond.class : ImageDeFond.java
    ${JC} ${JCFLAGS} ImageDeFond.java


Bloc.class : Bloc.java
    ${JC} ${JCFLAGS} Bloc.java

Score.class : Score.java
    ${JC} ${JCFLAGS} Score.java


### REGLES OPTIONNELLES ###

run : Main.class
    ${JVM} ${JVMFLAGS} Main

clean :
    -rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###