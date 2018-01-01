% 14.
% a. Define a predicate to determine the longest sequences of consecutive even numbers (if exist more maximal sequences one of them).
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% define a predicate to replace every sublist with the longest sequences of even numbers from that sublist.
% Eg.: [1, [2, 1, 4, 6, 7], 5, [1, 2, 3, 4], 2, [1, 4, 6, 8, 3], 2, [1, 5], 3] =>
% [1, [4, 6], 5, [2], 2, [4, 6, 8], 2, [], 3]

% a

% Model matematic:
% even(n) =
% 	ture, n % 2 = 0
% 	false, otherwise

% even(N:number)
% flow model: even(i)

even(N) :- N mod 2 =:= 0.

% Model matematic:
% my_append(l1...ln, e) =
% 	[e], n = 0
% 	l1 + my_append(l2...ln), otherwise

% my_append(L:list, E:number, R:list)
% flow model: my_append(i, i, o)

my_append([], E, [E]).
my_append([H|T], E, [H|R]) :-
    my_append(T, E, R).

% Model matematic:
% my_length(l1...ln) =
% 	0, n = 0
% 	1 + my_length(l2...ln), otherwise

% my_length(L:list, R:number)
% flow model: my_length(i, o)

my_length([], 0).
my_length([_|T], R) :-
    my_length(T, RC),
    R is RC + 1.

% Model matematic
% consecutive(l1...ln, c1...cm, r1...rl) =
% 	r1...rl, n = 0, l > m
% 	c1...cm, n = 0, l < m
% 	consecutive(l2...ln, c1...cm, l1 + r1...rl), even(l1) = true
% 	consecutive(l2...ln, r1...rl, []) even(l1) = false and l > m
% 	consecutive(l2...ln, c1...cm, []) even(l1) = false and l < m

% consecutive(L:list, C:list, AUX: list, R:list)
% consecutive(i, i, i, o)

consecutive([], C, AUX, C) :-
    my_length(C, RC),
    my_length(AUX, RAUX),
    RC >= RAUX.
consecutive([], C, AUX, AUX) :-
    my_length(C, RC),
    my_length(AUX, RAUX),
    RC < RAUX.
consecutive([H|T], C, AUX, R) :- even(H),
    my_append(AUX, H, RA),
    consecutive(T, C, RA, R).
consecutive([_|T], C, AUX, R) :-
    my_length(C, RC),
    my_length(AUX, RAUX),
    RAUX >= RC,
    consecutive(T, AUX, [], R).
consecutive([_|T], C, AUX, R) :-
    my_length(C, RC),
    my_length(AUX, RAUX),
    RAUX < RC,
    consecutive(T, C, [], R).

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
    consecutive(H, [], [], HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).
