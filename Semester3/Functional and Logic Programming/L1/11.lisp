; 11.
; a) Determine the least common multiple of the numerical values of a nonlinear list.

(defun _lcm (a b)
    (cond
        ((and (not (numberp a)) (not (numberp b))) nil)
        ((not (numberp a)) b)
        ((not (numberp b)) a)
        (T (/ (* a b) (_gcd a b)))
    )
)

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
        ((listp (car l)) (_lcm (list_gcd (car l)) (list_gcd (cdr l))))
        (T (_lcm (car l) (list_gcd (cdr l))))
    )
)

(print (list_gcd '(24 ( 16 (12 A B)) 72)))
;=> 144


; b) Write a function to test if a linear list of numbers has a "mountain" aspect (a list has a "mountain"
; aspect if the items increase to a certain point and then decreases.
;  Eg. (10 18 29 17 11 10). The list must have at least 3 atoms to fullfil this criteria.

(defun _mountain (l decreasing)
	(cond
		((= (list-length l) 1) (if decreasing nil T))
		((and (< (car l) (cadr l)) (not decreasing)) nil)
		((and (> (car l) (cadr l)) decreasing) (_mountain (cdr l) nil))
		(T (_mountain (cdr l) decreasing))
	)
)

(defun mountain (l)
	(if (< (list-length l) 3)
		nil
		(_mountain l T)
	)
)

(print (mountain '(10 18 29 17 11 10)))
; => T

(print (mountain '(10 18 29 17 11 29 10)))
;=> nil



; c) Remove all occurrences of a maximum numerical element from a nonlinear list.

(defun max_nb (a b)
    (if (> a b) a b)
)


(defun max_num (l)
    (cond
        ((null l) -1)
        ((and (null (cdr l)) (numberp (car l))) (car l))
        ((numberp (car l)) (max_nb (car l) (max_num (cdr l))))
        ((listp (car l)) (max_nb (max_num (car l)) (max_num (cdr l))))
        (t (max_num (cdr l)))
     )
)

(print (max_num '(1 2 (3 (a) (1 3)) 4 5)))
; => 5


; d) Write a function which returns the product of numerical even atoms from a list, to any level.
(defun productL (l)
    (cond
        ((numberp l) l)
        ((atom l) 1)
        (T (apply '* (mapcar 'productL l)))
    )
)

(print (productL '(1 2 3 (4 5 (6)) (7))))
;=> 5040