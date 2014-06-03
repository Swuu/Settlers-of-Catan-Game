# A very simple Makefile to support standart functionality
# Optimized way of compilation does not work correctly yet.

JC = javac
FLAGS = -d
DESTDIR = ./class_dir/
J_SRCS = *.java
J_PR_SRCS = CatanGame.java Player.java HexagonMap.java OpenTrade.java

J_PR_CLASSES = ./class_dir/CatanGame.class ./class_dir/Player.class \
	       ./class_dir/HexagonMap.class ./class_dir/OpenTrade.class

CLASSES = ./class_dir/*.class

MAIN_SRC = CatanGame.java
MAIN_CLASS = ./class_dir/CatanGame.class


std:
	@echo "Compiling game files"
	$(JC) $(FLAGS) $(DESTDIR) $(J_SRCS)
	@echo ""
	@echo "Done."

all: $(MAIN_SRC) $(J_PR_CLASSES)
	@echo "Compiling game files"
	$(JC) $(FLAGS) $(DESTDIR) $(MAIN_SRC)	
	@echo ""
	@echo "Done."

J_PR_CLASSES: $(J_PR_SRCS)
	$(JC) $(FLAGS) $(DESTDIR) $(J_PR_SRCS)

J_PR_SRCS: $(CLASSES)
	$(JC) $(FLAGS) $(DESTDIR) $(J_SRCS)

run: $(MAIN_CLASS)
	@echo "Starting ..."
	cd class_dir; java CatanGame
	@echo "The program was closed."

MAIN_CLASS: $(J_PR_CLASSES)
	$(JC) $(FLAGS) $(DESTDIR) $(MAIN_SRC)

clean:
	@echo "Cleaning up class directory ..."
	cd class_dir; rm -f *.class
	@echo ""
	@echo "Clean."

new:
	make --no-print-directory clean
	make --no-print-directory

