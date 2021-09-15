JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$( JC ) $( JFLAGS ) $*.java

CLASSPATH = ../bin

CLASSES = \
		  ./src/wagecalc/WageCalc.java \
		  ./src/foocorp/FooCorporation.java \
		  ./src/foocorp/Employee.java \
		  ./src/linkedlist/linkedList.java \
		  ./src/foocorp/InvalidBasePayException.java \
		  ./src/foocorp/InvalidHoursWorkedException.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	java ./wagecalc/WageCalc ../input/input.txt

clean:
	$(RM) *.class
