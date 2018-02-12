; 5.
; a) Write twice the n-th element of a linear list. Example: for (10 20 30 40 50) and n=3 will produce 
; (10 20 30 30 40 50).

(defun twice (l n)
    (cond
        ((null l) nil)
        ((= n 1) (cons (car l) l))
        (T (cons (car l) (twice (cdr l) (- n 1))))
    )
)

(print (twice '(10 20 30 40 50) 3))
;=> (10 20 30 30 40 50)


; b) Write a function to return an association list with the two lists given as parameters.
; Example: (A B C) (X Y Z) --> ((A.X) (B.Y) (C.Z)).

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun association (l k)
    (cond
        ((and (null l) (null k)) nil)
        ((null l) (my_append (list (cons NIL (car k))) (association l (cdr k))))
        ((null k) (my_append (list (cons (car l) NIL)) (association (cdr l) k)))
        (T (my_append (list (cons (car l) (car k))) (association (cdr l) (cdr k))))
    )
)

(print (association '(A B C Q) '(X Y Z T U)))
;=> ((A . X) (B . Y) (C . Z) (Q . T) (NIL . U)) 


; c) Write a function to determine the number of all sublists of a given list, on any level.
;  A sublist is either the list itself, or any element that is a list, at any level. Example:
;  (1 2 (3 (4 5) (6 7)) 8 (9 10)) => 5 lists:
;  (list itself, (3 ...), (4 5), (6 7), (9 10)).

(defun countList (l)
    (cond
        ((atom l) 0)
        ((listp l) (+ 1 (apply '+ (mapcar 'countList l))))
    )
)

(print (countList '(1 2 (3 (4 5) (6 7)) 8 (9 10))))


; d) Write a function to return the number of all numerical atoms in a list at superficial level.

(defun countNumbers (l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ 1 (countNumbers (cdr l))))
        (T (countNumbers (cdr l)))
    )
)

(print (countNumbers '(1 2 s 5 (6) fg 5)))
;=> 4