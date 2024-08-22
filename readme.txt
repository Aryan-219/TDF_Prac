Compiling only the listeners and the controllers won't work
we need to compile the elfuncs and models individually as well
because some of them are not connected to the controllers or listeners

javac -d ../classes elfuncs/*
javac -d ../classes models/*
javac -d ../classes controllers/*
javac -d ../classes listeners/*  