(ns learning.protocols-03-extending
  (:require [learning.protocols-01-basics :refer [Speakable speak greet]]))

;; =============================================================================
;; 1. The Expression Problem
;; =============================================================================
;; One of Clojure's greatest strengths is solving the "Expression Problem".
;; You can add a new protocol to an existing type (like String, nil, or a Java 
;; class) without modifying the original code.

;; =============================================================================
;; 2. extend-type
;; =============================================================================
;; Use extend-type when you want to add multiple protocols to ONE type.

(extend-type java.lang.String
  Speakable
  (speak [this]
    (str "I am a string: \"" this "\""))
  (greet [_this other]
    (str "String greeting to " (:name other))))

;; =============================================================================
;; 3. extend-protocol
;; =============================================================================
;; Use extend-protocol when you want to add ONE protocol to MULTIPLE types.

(extend-protocol Speakable
  ;; Handle nil (very useful for preventing NullPointerExceptions)
  nil
  (speak [_this] "Silence...")
  (greet [_this _other] "... (the void stares back)")

  ;; Handle numbers
  java.lang.Long
  (speak [this] (str "Number " this " reporting for duty."))
  (greet [_this other] (str "Calculated greeting to " (:name other))))

;; =============================================================================
;; 4. Usage
;; =============================================================================

(defn demo03 []
  (println "--- Extending Existing Types ---")
  (println "String speaking:" (speak "Hello World"))
  (println "Number speaking:" (speak 42))
  (println "Nil speaking:   " (speak nil))

  (println "\n--- Interaction with existing records ---")
  (let [alice (learning.protocols-01-basics/->Person "Alice" 30)]
    (println (greet "The Void" alice))
    (println (greet 100 alice))
    (println (greet nil alice))))

(demo03)
