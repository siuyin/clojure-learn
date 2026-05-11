.PHONY: test uberjar clean help

UBERJAR := target/protocols-basics-0.1.0-standalone.jar
SOURCES := $(shell find src -type f)

# Default target
help:
	@echo "Available targets:"
	@echo "  make test      - Run all project tests"
	@echo "  make uberjar   - Build an executable standalone JAR (only if sources changed)"
	@echo "  make clean     - Remove the target/ build directory"

test:
	clojure -X:test

uberjar: $(UBERJAR)

$(UBERJAR): $(SOURCES) deps.edn build.clj
	clojure -T:build uber

clean:
	clojure -T:build clean
