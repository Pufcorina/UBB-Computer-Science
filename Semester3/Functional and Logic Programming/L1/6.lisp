; 6.
; a) Write a function to test whether a list is linear.

(defun liniarList (l)
    (cond
        ((null l) T)
        ((listp (car l)) nil)
        (T (liniarList (cdr l)))
    )
)

(print (liniarList '(a (b c) (d (e (f))))))
;=> nil

(print (liniarList '(1 2 3 4)))
;=> T


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


; c) Write a function to replace each sublist of a list with its last element.
;  A sublist is an element from the first level, which is a list.
;  Example: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c f)
;  (a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun my_reverse (l)
    (if (null l)
        nil
        (my_append (my_reverse (cdr l)) (list (car l)))
    )        
)

(defun last_element (l)
	(if (listp l) 
        (last_element (car (my_reverse l)))
        l
    )
)

(print (last_element '(1 2 (3 (4 5)))))
; => 5

(defun _reduce (l)
	(cond
		((null l) nil)
		((listp (car l)) (cons (last_element (car l)) (_reduce (cdr l))))
		(T (cons (car l) (_reduce (cdr l))))
	)
)

(print (_reduce '(a (b c) (d (e (f))))))
; => '(a c f)

; d) Write a function to merge two sorted lists without keeping double values

(defun merge_sorted (a b)
	(cond
		((null a) b)
		((null b) a)
		((<= (car a) (car b)) (cons (car a) (merge_sorted (cdr a) b)))
		(T (cons (car b) (merge_sorted a (cdr b))))
	)
)

(print (merge_sorted '(1 2 3 3) '(3 4 4 5 5 7)))
; => (1 2 3 3 4 4 5 7)


(defun remove_doubles (l)
    (cond
        ((null l) nil)
        ((null (cdr l)) (list (car l)))
        ((= (car l) (cadr l)) (remove_doubles (cdr l)))
        (T (cons (car l) (remove_doubles (cdr l))))
    )
)

(print (remove_doubles '(1 2 3 3 4 4 5 7)))
;=> (1 2 3 4 5 7) 

(defun solve (a b)
    (remove_doubles (merge_sorted a b))
)

(print (solve '(1 2 3 3) '(3 4 4 5 5 7)))
;=> (1 2 3 4 5 7) 