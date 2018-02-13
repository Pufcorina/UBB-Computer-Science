; 8.
; a) Write a function to return the difference of two sets.

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

; L\K

(defun differenceSet (l k)
    (cond
         ((null l) nil)
         ((not (contains k (car l))) (cons (car l) (differenceSet (cdr l) k)))
         (T (differenceSet (cdr l) k))
    )
)

(print (differenceSet '(1 2 3 4 5) '(2 3 4 5 6 7)))
;=> (1)


; b) Write a function to reverse a list with its all sublists, on all levels.

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun my_reverse (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (my_append (my_reverse (cdr l)) (list (my_reverse (car l)))))
        (T (my_append (my_reverse (cdr l)) (list (car l))))
    )
)

(print (my_reverse '(1 2 (3 (4 5) (6 7)) 8 (9 10 11))))
;=> ((11 10 9) 8 ((7 6) (5 4) 3) 2 1) 


; c) Write a function to return the list of the first elements of all list elements of a given list with an odd
; number of elements at superficial level. Example:
;  (1 2 (3 (4 5) (6 7)) 8 (9 10 11)) => (1 3 9).

(defun my_length (l)
    (if (null l) 
        0
        (+ 1 (my_length (cdr l)))
    )
)

(defun odd_list_length (l)
    (= (mod (my_length l) 2) 1)
)

(print (odd_list_length '(1 2 3)))
; => T

(defun first_elem (l)
    (cond
        ((atom l) nil)
        ((odd_list_length l) (cons (car l) (apply 'append (mapcar 'first_elem (cdr l)))))
        (T (apply 'append (mapcar 'first_elem (cdr l))))
     )
)

(print (first_elem '(1 2 (3 (4 5) (6 7)) 8 (9 10 11))))
    


; d) Write a function to return the sum of all numerical atoms in a list at superficial level.

(defun sumSup (l)
    (cond
        ((numberp l) l)
        ((atom l) 0)
        ((listp l) (apply '+ (mapcar 'sumSup l)))
    )
)

(print (sumSup '(1 2 (3 (4 5) (6 7)) 8 (9 10 11))))
;=> 66