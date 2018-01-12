; 12. Write a function that substitutes an element through another one at all levels of a given list

(defun _replace (l e r)
    (cond
        ((and (numberp l) (if (= l e) r l)))
        ((listp l) (mapcar #'(lambda (a) (_replace a e r)) l))
    )
)

(print (_replace '(1 2 (1 2 (3 1) 2)) 1 0))