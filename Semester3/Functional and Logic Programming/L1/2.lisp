; 2
; a) Write a function to return the product of two vectors.
; https://en.wikipedia.org/wiki/Dot_product

(defun dot_product (a b)
    (if (null a)
        0
        (+ (* (car a) (car b)) (dot_product (cdr a) (cdr b)))
    )
)

(print (dot_product '(1 2 3) '(4 5 6)))
; => 1*4 + 2*5 + 3*6 = 32

; b) Write a function to return the depth of a list. Example: the depth of a linear list is 1.

(defun my_max (a b)
    (if (> a b) a b)
)

(defun depth_list (a l)
    (cond
        ((null a) l)
        ((listp (car a)) (my_max (depth_list (car a) (+ l 1)) (depth_list (cdr a) l)))
        (T (depth_list (cdr a) l))
    )
)

(print (depth_list '(6 (2 3 (4) (5) (6 (7)))) 0))
; => 3

; c) Write a function to sort a linear list without keeping the double values.

(defun insertOk (l e)
    (cond
        ((null l) (list e))
        ((equal (car l) e) l)
        ((< e (car l)) (cons e l))
        (T (cons (car l) (insertOk (cdr l) e)))
    )
)

(defun sortare (l)
    (cond
        ((null l) nil)
        (T (insertOk (sortare (cdr l)) (car l)))
    )
)

(print (sortare '(1 3 2 5 6 7 7 3 9)))
; => (1 2 3 5 6 7 9)

; d) Write a function to return the intersection of two sets.

(defun remove_first_ap (l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) (cdr l))
        (T (cons (car l) (remove_first_ap (cdr l) e)))
    )
)

(defun contains (l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) T)
        (T (contains (cdr l) e))
     )
)

(defun intersection_sets (l k)
    (cond
        ((or (null l) (null k)) nil)
        ((contains k (car l)) (cons (car l) (intersection_sets (cdr l) (remove_first_ap (cdr k) (car l)))))
        (T (intersection_sets (cdr l) k))
    )
)

(print (intersection_sets '(1 2 3 4 5) '(1 5 6 7 9)))
; => (1 5)
        