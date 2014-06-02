# A very simple Makefile to support standart functionality
#

all: *.java	
	@echo "Compiling game files"
	javac -d ./class_dir/ *.java
	@echo ""
	@echo "Done."

run: ./class_dir/CatanGame.class
	@echo "Starting ..."
	cd class_dir; java CatanGame
	@echo "The program was closed"

clean:
	@echo "Cleaning up class directory ..."
	cd class_dir; rm *.class
	@echo ""
	@echo "Clean."

new:
	make --no-print-directory clean
	make --no-print-directory

