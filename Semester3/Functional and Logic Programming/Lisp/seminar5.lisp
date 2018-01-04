; Probleme1: Media aritmetica a 3 numere date ca parametru

(defun ma (a b c)
    (/ (+ (+ a b) c) 3)
)
(setq media (ma 2 3 5) )
(print media)


; Problema2: Factorialul unui numar

; model matematic
; fact(n) = 
;    1, if n = 0 or n = 1
;	 n * fact(n - 1), otherwise

(defun fact (n)
    (cond
        ((or (equal n 0) (equal n 1)) 1)
        (T (* n (fact (- n 1))))
    )
)
(print (fact 5))


;Problema3: Interclasarea a 2 seturi sortate

;interclasare(l, p) =
;	- l, p = 0
;	- p, l = 0
;	- l1 reunit interclasare([l2 ... ln], [p1 ... pn]), l1 < p1
;	- p1 reunit interclasare([l1 ... ln], [p2 ... pn]), p1 < l1
;	- l1 reunit interclasare([l2 ... ln], [p2 ... pn]), l1 = p1

(defun interclasare (l p)
    (cond
        ((null l) p)
        ((null p) l)
        ((< (car l) (car p)) (cons (car l) (interclasare (cdr l) p)))
        ((> (car l) (car p)) (cons (car p) (interclasare l (cdr p))))
        ((equal (car l) (car p)) (cons (car l) (interclasare (cdr l) (cdr p))))
    )
)

(setq L (LIST 2 3 4 5))
(setq L '(2 3 4 5))
(setq P '(4 5 6 7))
(print (interclasare L P))