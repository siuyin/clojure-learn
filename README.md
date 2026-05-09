# Clojure Protocols and Datatypes Learning Series

This repository contains a series of progressive examples to help you learn Clojure's polymorphic systems: Protocols and Datatypes.

## Learning Path

### Phase 1: The Basics (`defprotocol` & `defrecord`)
- **Focus:** Understanding the fundamental way to define shared behavior and domain data.
- **Concepts:** `defprotocol`, `defrecord`, record instantiation, map-like properties.
- **Example:** `src/learning/protocols_01_basics.clj`

### Phase 2: Low-Level Types (`deftype`)
- **Focus:** When and why to use `deftype` instead of `defrecord`.
- **Concepts:** `deftype`, field access, performance considerations, lack of map-like behavior.
- **Example:** `src/learning/protocols_02_deftype.clj`

### Phase 3: Extending Existing Types
- **Focus:** Adding behavior to types you didn't define (Polymorphism without modification).
- **Concepts:** `extend-type`, `extend-protocol`, extending built-in types (String, nil).
- **Example:** `src/learning/protocols_03_extending.clj`

### Phase 4: Anonymous Implementation (`reify`)
- **Focus:** One-off implementations for flexibility.
- **Concepts:** `reify`, closures within implementations.
- **Example:** `src/learning/protocols_04_reify.clj`

## How to Run
You can run any example using the Clojure CLI:
```bash
clj -M src/learning/protocols_01_basics.clj
```
