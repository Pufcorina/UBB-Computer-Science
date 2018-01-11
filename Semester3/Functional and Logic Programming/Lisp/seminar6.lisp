; Seminar 6 - Functii MAP

; 1. Determinarea patratului unui nr.

(defun patrat(n) (* n n))

; + det patratul fiecarui element dintr-o lista : (2 3 4) => (4 9 16)

(defun map_patrat (l)
	(cond 
        ((null l) nil) 
        (T (cons (* (car l) (car l)) (map_patrat (cdr l))))
    )
)

(defun map_patrat (l)
	(cond 
        ((null l) nil) 
        (T (cons (patrat (car l)) (map_patrat (cdr l))))
    )
)

; - mapcar functie parametrii => det / return o lista dupa aplicarea functiei

(defun listaPatrat(lista)
	(mapcar 'patrat lista)
)

(defun listaPatrat(lista)
	(mapcar #'(lambda(x) (* x x)) lista)
)

(print (listaPatrat '(2 3 4)))


; 2. Sa se insumeze o constanta k la valorile unei liste liniare
; 2 , (6 8 20) => (8 10 12)

(defun sumaConst(k l)
    (mapcar #'(lambda (x) (+ k x)) l)
)

(print (sumaConst 2 '(6 8 20)))

; 3. Pentru o lista neliniara sa se produca modificari:
; (1 a 2 (3 b 4 (5 1))) => (2 a 4 (6 b 8 (10 2)))a

(defun sumConst2(l)
	(cond 
		((listp l) (mapcar 'sumConst2 l))
		((numberp l) (* l  2))
		((atom l) l)
	)
)

(print (sumConst2 '(1 a 2 (3 b 4 (5 1)))))

; (apply functie params) - return un singur rez

;(apply '+ (1 2 3 4 5)) ; => 15
;(apply 'max (1 2 3 4 5)) ; => 5

; 4. Produsul elem numerice dintr-o lista neliniara
; (2 a (3 b (4 5 c))) => 2 * 3 * 4 * 5

(defun produs(l)
	(cond
		((numberp l) l)
		((atom l) 1)
		((listp l) (apply '* (mapcar 'produs l)))
	)
)

(print (produs '(1 2 3 10))) ;- apelare

; model matematic:

; - l, l - numar
; - 1, l - atom
; - l1 * ... * ln, l - lista


; 5. Se da o lista neliniara. 
; Sa se determine nr sublistelor in care primul atom numeric la orice nivel are valoarea 5
; (a 5 (b c d) 2 1 (g (5 h) 7 d) 11 14)

; (5 h)
; (a 5 (b.....
; (g (5 h) 7 d)

; - liniarizarea liestei cu stergerea valorilor numerice
; - elim atomii nenumerici
; - sublistele

(defun lin_del(l)
	(cond
        ((null l) nil)
		((numberp (car l)) (cons (car l) (lin_del (cdr l))))
		((atom (car l))(lin_del (cdr l)))
		((listp (car l)) ( append (lin_del (car l)) (lin_del (cdr l))))
	)
)

(defun first5(l)
	(cond
		((null (lin_del l)) nil)
		((equal 5 (car (lin_del l))) T)
		(t nil)
	)
)
    
; numarare 
; subliste:
; - atom
; - lista + first5 is true
; - lista - first5 is false

; count(l) = 
; - 0, l = atom
; - 1 + suma i = 0 la n (numar(li)) , li - is_list and first5 is true
; - suma i = 0 la n (numar(li)), otherwise

(defun numar(l)
	(cond
		((atom l) 0)
		((and (listp l) (first5 l)) (+ 1 (apply ' + (mapcar ' numar l))))
		(T (apply '+ (mapcar ' numar l)))
	)
)

(print (numar '(a 5 (b c d) 2 1 (g (5 h) 7 d) 11 14)))


; Sa se determine numarul nodurilor de pe nivelele pare dintr-un arbore reprezentat ca si o lista

; 1 a
;   | \
; 2 b  c 
;   |
; 3 d
;   |
; 4 e

; (a (b (d (e))) (c))


; nr(l) = 
; - 1 , l - atom, nivel par
; - 0 , l - atom, nivel impar
; - noduriPare(li,nivel + 1),  li - lista .... sth with mapcar

(defun count2(l level)
	(cond
		((and (atom l) (= (mod level 2) 0)) 1)
		((and (atom l) (= (mod level 2) 1)) 0)
		; ((listp l) (apply '+ (mapcar ' count l (+ level 1))))  -> mapcar '+ (1 2 3) (1) se oppreste cand cea mai scurta lista se termina => functie lambda
		((listp l) (apply '+ (mapcar #' (lambda (a) (count2 a (+ 1 level))) l)))
	)
)

(print (count2 ' (a (b (d (e))) (c)) 0))