; 7.
; a) Write a function to eliminate the n-th element of a linear list.

(defun deleteN (l n)
    (cond
        ((null l) nil)
        ((= n 1) (cdr l))
        (T (cons (car l) (deleteN (cdr l) (- n 1))))
    )
)

(print (deleteN '(1 2 3 4 5 6 7 8) 5))

; b) Write a function to determine the successor of a number represented digit by digit as a list, without
; transforming the representation of the number from list to number. 
; Example: (1 9 3 5 9 9) --> (1 9 3 6 0 0)

(defun carry (n)
    (cond
        ((> (- n (mod n 10)) 9) (/ (- n (mod n 10)) 10))
        (T (- n (mod n 10)))
    )
)



(defun digit (n)
    (mod n 10)
)

(defun increaseNb (l)
    (cond
        ((null (cdr l)) (list (carry (+ 1 (car l))) (list (digit (+ 1 (car l))))))
        (T ((lambda (r)
                    (list (carry (+ (car l) (car r))) (cons (digit (+ (car l) (car r))) (cadr r)))
             ) (increaseNb (cdr l))
           )
        )
     )
)

(defun succesorNb (l)
    ((lambda (r) 
         (cond
            ((= (car r) 1) (cons 1 (cadr r)))
            (T (cadr r))
         ))
            (increaseNb l)
    )
)

(print (succesorNb '(1 9 3 5 9 9)))
(print (succesorNb '(9 9)))

; c) Write a function to return the set of all the atoms of a list.
;  Exemplu: (1 (2 (1 3 (2 4) 3) 1) (1 4)) ==> (1 2 3 4)

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun lin (l)
    (cond
        ((null l) nil)
        ((numberp (car l)) (cons (car l) (lin (cdr l))))
        ((listp (car l)) (my_append (lin (car l)) (lin (cdr l))))
        (T (lin (cdr l)))
    )
)

(print (lin '(1 (2 (1 3 (2 4) 3) 1) (1 4))))
;=> (1 2 1 3 2 4 3 1 1 4) 

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

(defun solve (l)
    (sortare (lin l))
)

(print (solve '(1 (2 (1 3 (2 4) 3) 1) (1 4))))
;=> (1 2 3 4)

; d) Write a function to test whether a linear list is a set.

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun checkSet (l)
    (cond
        ((null l) T)
        ((contains (cdr l) (car l)) nil)
        (T (checkSet (cdr l)))
    )
)

(print (checkSet '(1 2 3 4)))
;=> T

(print (checkSet '(1 2 2 3 4)))
;=> nil