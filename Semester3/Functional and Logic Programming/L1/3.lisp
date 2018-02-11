; 3.
; a) Write a function that inserts in a linear list a given atom A after the 2nd, 4th, 6th, ... element.

(defun insert_el (l e p)
    (cond
        ((null l) nil)
        ((equal (mod p 2) 0) (cons (car l) (cons e (insert_el (cdr l) e (+ p 1)))))
        (T (cons (car l) (insert_el (cdr l) e (+ p 1))))
    )
)

(defun solve (l e)
    (insert_el l e 1)
)

(print (solve '(1 2 3 4 5 6 7 8) 78))
;=> (1 2 78 3 4 78 5 6 78 7 8 78) 


; b) Write a function to get from a given list the list of all atoms, on any
;  level, but reverse order. Example:
;  (((A B) C) (D E)) ==> (E D C B A)

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun all_atoms (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (my_append (all_atoms (cdr l)) (all_atoms (car l))))
        (T (my_append (all_atoms (cdr l)) (list (car l))))
    )
)

(print (all_atoms '(((A B) C) (D E))))

; c) Write a function that returns the greatest common divisor of all numbers in a nonlinear list.

(defun _gcd (a b)
    (cond
        ((and (not (numberp a)) (not (numberp b))) nil)
        ((not (numberp a)) b)
        ((not (numberp b)) a)
        ((equal b 0) a)
        (T (_gcd b (mod a b)))
    )
)

(defun list_gcd (l)
    (cond
        ((and (atom (car l)) (null (cdr l))) (car l))
        ((listp (car l)) (_gcd (list_gcd (car l)) (list_gcd (cdr l))))
        (T (_gcd (car l) (list_gcd (cdr l))))
    )
)

(print (list_gcd '(24 ( 16 (12 A B)) 72)))
;=> 4

; d) Write a function that determines the number of occurrences of a given atom in a nonlinear list.

(defun occurences (l e)
    (cond
        ((and (atom l) (equal l e)) 1)
        ((atom l) 0)
        (T (apply '+ (mapcar #'(lambda (a) (occurences a e)) l)))
    )
)

(print (occurences '(1 (3 (5 4 3) (5 3)) 3 3) 3))
;=> 5