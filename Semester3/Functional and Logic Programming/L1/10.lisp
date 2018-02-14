; 10.
; a) Write a function to return the product of all the numerical atoms from a list, at superficial level.

(defun productL (l)
    (cond
        ((null l) 1)
        ((numberp (car l)) (* (car l) (productL (cdr l))))
        (T (productL (cdr l)))
    )
)

(print (productL '(1 A 2 3 (3 4 5) T)))
;=> 6


; b) Write a function to replace the first occurence of an element E in a given list with an other element O.

(defun replaceFirstElement (l e o)
    (cond
        ((null l) nil)
        ((= (car l) e) (cons o (cdr l)))
        (T (cons (car l) (replaceFirstElement (cdr l) e o)))
    )
)

(print (replaceFirstElement '(1 2 3 4 5 6 7 6 4 6) 6 0))
;=> (1 2 3 4 5 0 7 6 4 6) 

; c) Write a function to compute the result of an arithmetic expression memorised
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


; d) Write a function to produce the list of pairs (atom n), where atom appears for n times in the
; parameter list. Example:
;  (A B A B A C A) --> ((A 4) (B 2) (C 1)).

(defun nr_app (l e)
    (cond
        ((null l) 0)
        ((equal (car l) e) (+ 1 (nr_app (cdr l) e)))
        (T (nr_app (cdr l) e))
    )
)

(defun remov (l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) (remov (cdr l) e))
        (T (cons (car l) (remov (cdr l) e)))
    )
)

(defun solve (l)
    (cond
        ((null l) nil)
        (T (cons (list (car l) (nr_app l (car l))) (solve (remov (cdr l) (car l)))))
    )
)

(print (solve '(A B A B A C A)))
;=> ((A 4) (B 2) (C 1))