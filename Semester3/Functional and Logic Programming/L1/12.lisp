; 12.
; a) Write a function to return the dot product of two vectors. https://en.wikipedia.org/wiki/Dot_product

(defun dot_product (a b)
    (if (null a)
        0
        (+ (* (car a) (car b)) (dot_product (cdr a) (cdr b)))
    )
)

(print (dot_product '(1 2 3) '(4 5 6)))
; => 1*4 + 2*5 + 3*6 = 32



; b) Write a function to return the maximum value of all the numerical atoms of a list, at any level.

(defun maxL (l)
    (cond
        ((numberp l) l)
        ((atom l) -1)
        (T (apply 'max (mapcar 'maxL l)))
    )
)

(print (maxL '(1 A (B C) ((5) 8))))
;=> 8


; c) All permutations to be replaced by: Write a function to compute the result of an arithmetic
; expression memorised
;  in preorder on a stack. Examples:
;  (+ 1 3) ==> 4 (1 + 3)
;  (+ * 2 4 3) ==> 11 [((2 * 4) + 3)
;  (+ * 2 4 - 5 * 2 2) ==> 9 ((2 * 4) + (5 - (2 * 2))

(defun _expression (op a b)
	(cond
		((string= op "+") (+ a b))
		((string= op "-") (- a b))
		((string= op "*") (* a b))
		((string= op "/") (floor a b))
	)
)

(defun expression (l)
    (cond
        ((null l) nil)
        ((and (and (numberp (cadr l)) (numberp (caddr l))) (atom (car l))) (cons (_expression (car l) (cadr l) (caddr l)) (expression (cdddr l))))
        (T (cons (car l) (expression (cdr l))))
    )
)

(defun solve (l)
    (cond
        ((null (cdr l)) (car l))
        (T (solve (expression l)))
    )
)

(print (solve '(+ * 2 4 - 5 * 2 2)))
; => 9 ((2 * 4) + (5 - (2 * 2))


; d) Write a function to return T if a list has an even number of elements on the first level, and NIL on
; the contrary case, without counting the elements of the list.

(defun even_length (l)
    (cond
        ((null l) T)
        ((null (cdr l)) nil)
        (T (even_length (cddr l)))
    )
)

(print (even_length '(1 2 2 4)))
; => T

(print (even_length '(1 2 4 2 4)))
; => nil