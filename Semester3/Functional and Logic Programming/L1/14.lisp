; 14.
; a) Write a function to return the union of two sets.

(defun remove_first_ap (l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) (cdr l))
        (T (cons (car l) (remove_first_ap (cdr l) e)))
    )
)

(defun union_sets (l k)
    (cond
        ((and (null l) (null k)) nil)
        ((null l) k)
        ((null k) l)
        (T (cons (car l) (union_sets (cdr l) (remove_first_ap k (car l)))))
    )
)

(print (union_sets '(1 2 3 4 5) '(1 5 6 7 9)))

; b) Write a function to return the product of all numerical atoms in a list, at any level.

(defun prod (l)
    (cond 
        ((null l) 1)
        ((listp (car l)) (* (prod (car l)) (prod (cdr l))))
        ((numberp (car l)) (* (car l) (prod (cdr l))))
        (T (prod (cdr l)))
    )
)

(print (prod '(5 ( 1 2 3 (6 (8 9) 7)))))

; c) Write a function to sort a linear list with keeping the double values.

(defun insertOk (l e)
    (cond
        ((null l) (list e))
        ((or (equal (car l) l) (< e (car l))) (cons e l))
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

; d) Build a list which contains positions of a minimum numeric element from a given linear list.

(defun my_min (a b)
    (if (< a b) a b)
)

(defun pos (l e i)
    (cond
        ((null l) nil)
        ((equal (car l) e) (cons i (pos (cdr l) e (+ i 1))))
        (T (pos (cdr l) e (+ i 1)))
    )
)

(defun my_length (l)
    (cond
        ((atom l) 1)
        (T (apply '+ (mapcar 'my_length l)))
    )
)

(defun min_list (l)
    (if (equal (my_length l) 1)
        (car l)
        (my_min (car l) (min_list (cdr l)))
    )
)

(defun solve (l)
    (pos l (min_list l) 1)
)

(print (solve '(3 2 5 6 2 7 7 2 3 9)))