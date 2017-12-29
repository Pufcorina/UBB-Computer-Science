% 10.
% a. For a list of integer numbers, define a predicate to write twice in list every prime number.
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% define a predicate to write in every sublist twice every prime number.
% Eg.: [1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5] =>
% [1, [2, 2, 3, 3], 4, 5, [1, 4, 6], 3, [1, 3, 3, 7, 7, 9, 10], 5]

% a

% Model matematic:
% nrPrim(n, div) =
% 	ture, n <= 3
% 	true, n % div != 0 and div >= n / 2
% 	nrPrim(n, div + 2), n % div != 0 and div < n / 2
% 	false, otherwise

% nrPrim(N:number, Div:number)
% flow model: nrPrim(i, i)

nrPrim(N, _) :- N > 0, N =< 3.
nrPrim(N, Div) :- N mod Div =\= 0,
    Div >= N / 2, !.
nrPrim(N, Div) :- N mod Div =\= 0,
    NDiv is Div + 2,
    nrPrim(N, NDiv).

% Model matematic:
% primeTwice(l1...ln) =
% 	[], n = 0
% 	l1 + l1 + primeTwice(l2...ln), nrPrim(l1, 3) = True,
% 	l1 + primeTwice(l2...ln), otherwise

% primeTwice(L:list, R:list)
% flow model: primeTwice(i, o)

primeTwice([], []).
primeTwice([H|T], [H,H|R]) :- nrPrim(H, 3), !,
    primeTwice(T, R).
primeTwice([H|T], [H|R]) :-
    primeTwice(T, R).

% b

% Model matematic:
% heterList(l1...ln, list) = 
% 	[], n = 0
% 	primeTwice(l1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([E], [E]).
heterList([H|T], [HR|R]) :- is_list(H), !,
    primeTwice(H, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).