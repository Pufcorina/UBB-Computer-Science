; 13. For a given tree of type (2) return the path from the root node to a certain given node X.

(defun apare (l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) t )
        (t (or (apare (cadr l) e) (apare (caddr l) e)))
    )
)

(defun cale (l e)
    (cond
        ((not (apare l e)) nil)
        ((equal (car l) e)(list  e) )
        ((apare (cadr l) e) (cons (car l) (cale (cadr l) e)))
        (t (cons (car l) (cale (caddr l) e)))
    )
)    

(print (cale '(A (B) (C (D) (E))) 'D))

(defun calea (l e)    
    (cond
        ((null l) nil)
        ((equal (car l) e) (list e))
        (t 
         ((lambda (l e x y)
                 (cond
                     ((and (null x) (null y)) nil)
                     (t (cons (car l) (append x y)))
                 ))
                 l e (calea (cadr l) e) (calea (caddr l) e))

        )
    )
)

(print (calea '(A (B) (C (D) (E))) 'D))