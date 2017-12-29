% 11.
% a. Replace all occurrences of an element from a list with another element e.
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% define a predicate to determine the maximum number of the list, 
% and then to replace this value in sublists with the maximum value of sublist.
% Eg.: [1, [2, 5, 7], 4, 5, [1, 4], 3, [1, 3, 5, 8, 5, 4], 5, [5, 9, 1], 2] =>
% [1, [2, 7, 7], 4, 5, [1, 4], 3, [1, 3, 8, 8, 8, 4], 5, [9, 9, 1], 2]


% a

% Model matematic:
% replaceEl(l1...ln, e1, e2) =
% 	[], n = 0
% 	e2 + replaceEl(l2...ln, e1, e2), l1 = e1
% 	l1 + replaceEl(l2...ln, e1, e2), otherwise

% replaceEl(L:list, E1:number, E2:number, R:list)
% flow model: replaceEl(i, i, i, o)

replaceEl([], _, _, []).
replaceEl([H|T], E1, E2, [E2|R]) :- H =:= E1, !,
    replaceEl(T, E1, E2, R).
replaceEl([H|T], E1, E2, [H|R]) :-
    replaceEl(T, E1, E2, R).

% b

% Model matematic:
% maxim_number(a, b) = 
% 	a, a >= b
% 	b a < b

% maxim_number(A:number, B:number, R:number)
% maxim_number(i, i, o)

maxim_number(A, B, A) :- A >= B.
maxim_number(A, B, B) :- A < B.

% Model matematic:
% maxim_list(l1...ln) =
% 	l1, n = 1
% 	maxim_number(l1, maxim_list(l2...ln)), number(l1) = True
% 	 maxim_list(l2...ln), otherwise
	
% maxim_list(L:list, R:number)
% maxim_list(i, o)

maxim_list([H], H).
maxim_list([H|T], R) :- number(H), !,
    maxim_list(T, RM),
    maxim_number(H, RM, R).
maxim_list([_|T], R) :- 
    maxim_list(T, R).

% Model matematic:
% heterList(l1...ln, m) = 
% 	[], n = 0
% 	replaceEl(l1, m, maxim_list(l1)) + heterList(l2...ln, m), is_list(l1) = True
% 	l1 + heterList(l2...ln, m), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], _, []).
heterList([H|T], M, [HR|R]) :- is_list(H), !,
    maxim_list(H, RM),
    replaceEl(H, M, RM, HR),
    heterList(T, M, R).
heterList([H|T], M, [H|R]) :-
    heterList(T, M, R).

% mainHeter(L:list, R:list)
% flow model: heterList(i, o)

mainHeter(L, R) :- 
    maxim_list(L, RM),
    heterList(L, RM, R).
    