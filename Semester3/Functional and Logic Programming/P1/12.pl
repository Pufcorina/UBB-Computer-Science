% 12.
% a. Write a predicate to substitute in a list a value with all the elements of another list.
% b. Remove the n-th element of a list

% a

% Model matematic:
% inserare(l1...ln, list) =
% 	list, n = 0
% 	l1 + inserare(l2...ln, list), otherwise
 
% inserare(L:list, List:list, R:list)
% inserare(i, i, o)

inserare([], L, L).
inserare([H|T], L, [H|R]) :- inserare(T, L, R).

% Model matematic:
% substitue_elem(l1...ln, e, list) =
% 	[], n = 0
% 	substitue_elem(inserare(list, l2...ln), e, list), l1 = e
% 	l1 + substitue_elem(l2...ln, e, list), l1 != e

% substitue_elem(L:list, E:number, List:list, R:list)
% substitue_elem(i, i, i, o)

substitue_elem([], _, _, []).
substitue_elem([H|T], E, List, R) :- H =:= E,
    inserare(List, T, RI),
    substitue_elem(RI, E, List, R).
substitue_elem([H|T], E, List, [H|R]) :- H =\= E,
    substitue_elem(T, E, List, R).
% b

% Model matematic:
% remove_n(l1...ln, k) =
% 	[], n = 0
% 	l2...ln, k = 1
% 	l1 + remove_n(l2...ln, k -1), otherwise

% remove_n(L:list, K:number, R:list)
% remove_n(i, i, o)

remove_n([], _, []).
remove_n([_|T], 1, T).
remove_n([H|T], K, [H|R]) :-
    K1 is K - 1,
    remove_n(T, K1, R).