; 15.
; a) Write a function to insert an element E on the n-th position of a linear list.

(defun insertN (l e n)
    (cond
        ((equal n 1) (cons e l))
        (T (cons (car l) (insertN (cdr l) e (- n 1))))
    )
)

(print (insertN '(1 2 3 4) 'A '2))


; b) Write a function to return the sum of all numerical atoms of a list, at any level.

(defun suma (l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ (car l) (suma (cdr l))))
        ((listp (car l)) (+ (suma (car l)) (suma (cdr l))))
        (T (suma (cdr l)))
    )
)

(print (suma '(1 2 (3 4 (A 8) (B)))))


; c) Write a function to return the set of all sublists of a given linear list. Ex. For list ((1 2 3) ((4 5) 6)) =>
; ((1 2 3) (4 5) ((4 5) 6))

(defun sublists (l) 
    (cond
        ((null l) nil)
        ((listp (car l)) (append (list (car l))  (sublists (car l)) (sublists (cdr l))))
        (T (sublists (cdr l)))
    )
)

(print (sublists '((1 2 3) ((4 5) 6))))


; d) Write a function to test the equality of two sets, without using the difference of two sets.

(defun remov (l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) (remov (cdr l) e))
        (T (cons (car l) (remov (cdr l) e)))
    )
)

(defun set= (l k)
    (cond
        ((and (null l) (null k)) T)
        ((or (null l) (null k)) nil)
        (T (set= (cdr l) (remov k (car l))))
    )
)

(print (set= '(1 2 7 3) '(1 6 2 3)))