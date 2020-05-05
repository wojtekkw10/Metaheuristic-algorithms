all:
	gradle shadowjar
	chmod +x main

run: all
	./main <input >output 2>err

test: all
	./main <input
