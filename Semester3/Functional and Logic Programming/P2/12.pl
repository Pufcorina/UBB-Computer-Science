% 12.
% a. Define a predicate to add after every element from a list, the divisors of that number.
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% define a predicate to add in every sublist the divisors of every element.
% Eg.: [1, [2, 5, 7], 4, 5, [1, 4], 3, 2, [6, 2, 1], 4, [7, 2, 8, 1], 2] =>
% [1, [2, 5, 7], 4, 5, [1, 4, 2], 3, 2, [6, 2, 3, 2, 1], 4, [7, 2, 8, 2, 4, 1], 2]

% a

% Model matematic:
% insert_div(l1...ln, div, list) =
% 	list, n <= 2 or n = div
% 	div + insert_div(l2...ln, div + 1, list), n % div = 0
% 	insert_div(l2...ln, div + 1, list), otherwise

% insert_div(N:number, Div:number, L:list, R:list)
% flow model: insert_div(i, i, i, o)

insert_div(N, _, L, L) :- N =< 2.
insert_div(N, N, L, L).
insert_div(N, Div, L, [Div|R]) :- N mod Div =:= 0, !,
    NDiv is Div + 1,
    insert_div(N, NDiv, L, R).
insert_div(N, Div, L, R) :-
    NDiv is Div + 1,
    insert_div(N, NDiv, L, R).

% divizori(l1...ln) =
% 	[], n = 0
% 	insert_div(l1, 2) + divizori(l2...ln), otherwise

% divizori(L:list, R:list)
% flow model: divizori(i, o)

divizori([], []).
divizori([H|T], [H|R]) :-
    divizori(T, RD),
    insert_div(H, 2, RD, R).

% b

% Model matematic:
% heterList(l1...ln) = 
% 	[], n = 0
% 	divizori(l1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    divizori(H, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).
    