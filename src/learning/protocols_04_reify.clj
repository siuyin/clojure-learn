(ns learning.protocols-04-reify
  (:require [learning.protocols-01-basics :refer [Speakable speak greet]]))

;; =============================================================================
;; 1. What is reify?
;; =============================================================================
;; reify allows you to create an anonymous implementation of one or more 
;; protocols (or interfaces) on the fly.
;;
;; Key features:
;; - No named type is created.
;; - It creates a closure, meaning it can "capture" local variables.
;; - Perfect for mocks, callbacks, or one-off implementations.

(defn make-custom-speaker [my-name custom-sound]
  ;; reify captures 'my-name' and 'custom-sound' from the surrounding scope
  (reify Speakable
    (speak [this]
      (str "I am " my-name " and I say: " custom-sound))
    (greet [this other]
      (str my-name " waves at " (:name other)))))

;; =============================================================================
;; 2. Usage & Comparison
;; =============================================================================

(defn demo []
  (println "--- reify in Action ---")
  
  (let [robot (make-custom-speaker "Robo-9000" "Beep Boop")
        ghost (make-custom-speaker "Casper" "Boo!")]
    
    (println (speak robot))
    (println (speak ghost))

    (println "\n--- Interaction with records ---")
    (let [alice (learning.protocols-01-basics/->Person "Alice" 30)]
      (println (greet robot alice))
      (println (greet ghost alice)))

    (println "\n--- Type Information ---")
    ;; Notice the type name is generated and anonymous
    (println "Robot type:" (type robot))
    (println "Is robot a Speakable?" (satisfies? Speakable robot))
    
    ;; Unlike records, reified objects are NOT maps
    (println "Is robot a map?" (map? robot))))

(demo)
