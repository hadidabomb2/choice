# Java Template Notes

This template demonstrates the decorator (annotation-based) and factory patterns using only the Java standard library.

Includes:
- `app/Main.java` — demo of annotation perf + factory
- `decorator/Perf.java` — @Perf annotation
- `decorator/PerfTracker.java` — reflection-based perf tracker
- `factory/Shape.java`, `factory/Circle.java`, `factory/Square.java`, `factory/ShapeFactory.java` — factory example

Run:
- `javac app/Main.java decorator/*.java factory/*.java`
- `java app.Main`