(ns learning.protocols-02-deftype)

;; =============================================================================
;; 1. What is deftype?
;; =============================================================================
;; deftype is similar to defrecord but much lower-level.
;; 
;; Use defrecord for:
;; - Domain data (entities, business objects)
;; - When you want map-like behavior (assoc, keys, keyword lookup)
;; 
;; Use deftype for:
;; - Low-level infrastructure (buffers, queues, custom collections)
;; - Performance-critical code (fewer overheads than records)
;; - When you DON'T want map-like behavior (to prevent accidental map usage)

(defprotocol PStack
  (push [this item] "Adds an item to the stack.")
  (pop-item [this] "Removes and returns the top item.")
  (peek-item [this] "Returns the top item without removing it."))

;; =============================================================================
;; 2. Implementing a Stack with deftype
;; =============================================================================
;; Note: deftype fields are NOT map-keys. You can't use (:items my-stack).
;; Fields can be volatile or mutable (but stay idiomatic with atoms for state).

(deftype SimpleStack [state]
  PStack
  (push [this item]
    (swap! state conj item)
    this)
  (pop-item [this]
    (let [top (peek @state)]
      (swap! state pop)
      top))
  (peek-item [this]
    (peek @state)))

;; =============================================================================
;; 3. Usage & Comparison
;; =============================================================================

(defn demo []
  (let [stack (SimpleStack. (atom []))]
    
    (println "--- deftype in Action ---")
    (push stack "First")
    (push stack "Second")
    (println "Peeked:" (peek-item stack))
    (println "Popped:" (pop-item stack))
    (println "Peeked again:" (peek-item stack))

    (println "\n--- Why it differs from defrecord ---")
    
    ;; 1. No keyword lookup
    (println "Attempting keyword lookup (:state stack):" (:state stack) "(Returns nil)")
    
    ;; 2. Not a map
    (try
      (keys stack)
      (catch Exception e (println "Calling (keys stack) fails as expected.")))

    (println "\n--- Type Information ---")
    (println "Is it a SimpleStack?" (instance? SimpleStack stack))
    (println "Does it satisfy PStack?" (satisfies? PStack stack))))

(demo)
