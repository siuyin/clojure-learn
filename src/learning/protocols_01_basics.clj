(ns learning.protocols-01-basics)

;; =============================================================================
;; 1. What is a Protocol?
;; =============================================================================
;; A protocol is a named set of operations. It's similar to an Interface in 
;; other languages, but more flexible because you can extend it to existing 
;; types without changing their source code.

(defprotocol Speakable
  "A protocol for things that can speak or make sounds."
  (speak [_this] "Returns a string representing the speech.")
  (greet [_this other] "Greets another entity."))

;; =============================================================================
;; 2. What is a Record?
;; =============================================================================
;; defrecord is the idiomatic way to define a "class" for domain data. 
;; Records are map-like: they support keyword lookups, assoc, dissoc, etc.
;; They also support type-based dispatch via protocols.

(defrecord Person [name age]
  Speakable
  (speak [_this]
    (str "Hello! My name is " name "."))
  (greet [_this other]
    (str "Hello " (:name other) ", I'm " name ". Nice to meet you!")))

(defrecord Dog [name breed]
  Speakable
  (speak [_this]
    "Woof! Woof!")
  (greet [_this other]
    (str "Wags tail at " (:name other) ".")))

;; =============================================================================
;; 3. Usage & Demonstration
;; =============================================================================

(defn demo01 []
  (let [alice (->Person "Alice" 30)
        fido  (->Dog "Fido" "Golden Retriever")]

    (println "--- Basic Protocol Dispatch ---")
    (println "Alice says:" (speak alice))
    (println "Fido says: " (speak fido))

    (println "\n--- Interaction ---")
    (println (greet alice fido))
    (println (greet fido alice))

    (println "\n--- Map-like properties of Records ---")
    (println "Alice's name via keyword lookup:" (:name alice))
    (println "Update Alice's age via assoc:" (assoc alice :age 31))
    (println "Keys in Alice record:" (keys alice))

    (println "\n--- Type Information ---")
    (println "Is Alice a Person?" (instance? Person alice))
    (println "Does Alice satisfy Speakable?" (satisfies? Speakable alice))))

;; Run the demo
;;(demo01)
