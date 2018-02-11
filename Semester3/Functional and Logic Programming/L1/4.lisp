; 4.
; a) Write a function to return the sum of two vectors.

(defun vector_sum (a b)
    (if (null a)
        nil
        (cons (+ (car a) (car b)) (vector_sum (cdr a) (cdr b)))
    )
)

(print (vector_sum '(1 2 3) '(4 5 6)))
; => (5 7 9)


; b) Write a function to get from a given list the list of all atoms, on any
;  level, but on the same order. Example:
;  (((A B) C) (D E)) ==> (A B C D E)

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun all_atoms (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (my_append (all_atoms (car l)) (all_atoms (cdr l))))
        (T (cons (car l) (all_atoms (cdr l))))
    )
)

(print (all_atoms '(((A B) C) (D E))))
;=> (A B C D E)

; c) Write a function that, with a list given as parameter, inverts only continuous
;  sequences of atoms. Example:
;  (a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))

(defun my_reverse (l)
    (if (null l)
        nil
        (my_append (my_reverse (cdr l)) (list (car l)))
    )        
)

(defun inv_list (l aux)
    (cond
        ((null l) (my_reverse aux))
        ((listp (car l)) (my_append (my_reverse aux) (cons (inv_list (car l) nil) (inv_list (cdr l) nil))))
        (T (inv_list (cdr l) (my_append aux (list (car l)))))
    )
)

(print (inv_list '(a b c (d (e f) g h i)) nil))


; d) Write a list to return the maximum value of the numerical atoms from a list, at superficial level.

(defun my_max (a b)
    (cond
        ((and (not (numberp a)) (not (numberp b))) nil)
        ((not (numberp a)) b)
        ((not (numberp b)) a)
        ((> a b) a)
        (T b)
    )
)

(defun max_list (l)
    (if (null (cdr l)) 
        (car l)
        (my_max (car l) (max_list (cdr l)))
    )
)

(print (max_list '(A 1 B 4 5 3 C F)))