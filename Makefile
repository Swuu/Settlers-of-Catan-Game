.SUFFIXES: .java .class
BIN=./bin/
SOURCE=./src/
.java.class:
	javac $<

main: gui network trade map ./bin/CatanGame.class

map: settlement player ./bin/Hexagon.class ./bin/Coord.class ./bin/Port.class ./bin/HexagonMap.class

settlement: ./bin/SettlementShape.class ./bin/GameSettlement.class

gui: ./bin/CatanSetup.class ./bin/NetworkFrame.class ./bin/WaitingRoom.class ./bin/TitleScreen.class

network: ./bin/NetworkController.class ./bin/HostController.class ./bin/ClientController.class

player: cards ./bin/Player.class

cards: ./bin/DevelopmentCard.class ./bin/ResourceCard.class 

trade: ./bin/OpenTrade.class

bin/%.class: ./src/%.java
	javac -cp .:lib/objectdraw.jar:./src -d ./bin $^

run: main
	make -C bin/ run

jar: main
	cp bin/*.class jar/
	make -C jar/ new
	make -C jar/ clean
	


