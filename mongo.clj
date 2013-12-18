;; DOC: http://clojuremongodb.info/articles/getting_started.html
(require '[monger.core :as mg]
         '[monger.collection :as mgc])

(import '[com.mongodb MongoOptions ServerAddress])
(import '[org.bson.types ObjectId]
        '[com.mongodb DB WriteConcern])

;; (mongo-connect { :host "db.megacorp.internal" :port 7878 })
(defn mongodb-connect! [addr]
  (mg/connect! addr)
  (mg/set-db! (mg/get-db "riemann")))

;; Examples:
;;
;; 1. changedb 
;; (mg/set-db! (mg/get-db "monger-test"))
;;
;; 2. insert 
;; * with explicit document id (recommended) 
;;   (mgc/insert "documents" { :_id (ObjectId.) :first_name "John" :last_name "Lennon" })
;; * without document id (when you don't need to use it after storing the document)
;;   (insert "document" { :first_name "John" :last_name "Lennon" })
;;
;; 3. multiple documents at once
;; (mgc/insert-batch "document" [{ :first_name "John" :last_name "Lennon" }
;;                               { :first_name "Paul" :last_name "McCartney" }])

(defn mongo-save-event 
  ([coll]
     (fn [event] (mgc/insert coll event)))
  ([db coll]
     (fn [event] (mgc/insert db coll event))))

(defn mongo-save 
  ([coll dict]
     (fn [event] (mgc/insert coll dict)))
  ([db coll dict]
     (fn [event] (mgc/insert db coll dict))))

