% 15.
% a. Define a predicate to determine the predecessor of a number represented as digits in a list.
% Eg.: [1 9 3 6 0 0] => [1 9 3 5 9 9]
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% define a predicate to determine the predecessor of the every sublist considered as numbers.
% Eg.: [1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>
% [1, [2, 2], 4, 5, [6, 7, 8], 10, 11, [1, 1, 9] 6]

% a

% Model matematic:
% predecesor(n, c) =
% 	[], n = 0
% 	[9], n = 1 and l1 = 0
% 	[l1 - 1], n = 1 and l1 != 0
% 	9 + predecesor(l2...ln, 1), c = 1 and l1 = 0
% 	(l1 - c) + predecesor(l2...ln, c), otherwise

% predecesor(L:list, C:number, R:list)
% flow model: predecesor(i, i, o)

predecesor([], _, []) :- !.
predecesor([0], 1, [9]) :- !.
predecesor([N], 0, [NR]) :- NR is N - 1, !.
predecesor([0|T], 1, [9|R]) :-
    predecesor(T, 1, R), !.
predecesor([H|T], 0, [HR|R]) :-
    predecesor(T, C, R),
    HR is H - C.

% b

% Model matematic:
% heterList(l1...ln, list) = 
% 	[], n = 0
% 	consecutive(l1, [], []) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    predecesor(H, _, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).
