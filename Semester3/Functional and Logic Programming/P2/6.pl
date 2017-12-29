% 6.
% a. Determine the product of a number represented as digits in a list to a given digit.
% Eg.: [1 9 3 5 9 9] * 2 => [3 8 7 1 9 8]
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% write a predicate to replace every sublist with the position of the maximum element from that sublist.
% [1, [2, 3], [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] =>
% [1, [2], [1, 3], 3, 6, [2], 5, [1, 2, 3], 7]

% a

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
% inv_list(l1...ln) =
% 	[], n = 0
% 	my_append(inv_list(l2...ln), l1), otherwise

% inv_list(L:list, R:list)
% flow model: inv_list(i, o)

inv_list([], []).
inv_list([H|T], R) :-
    inv_list(T, RI),
    my_append(RI, H, R).

% Model matematic:
% product(l1...ln, e, c) =
% 	[c], n = 0
% 	((l1*e + c) % 10) + product(l2...ln, e, ((l1*e + c)/10)), otherwise

% product(L:list, E:number, C:number, R:list)
% flow model: product(i, i, i, o)

product([], _, 0, []).
product([], _, C, [C]) :- C =\= 0.
product([H|T], E, C, [HR|R]) :-
    HR is (H * E + C) mod 10,
    NC is (H * E + C - HR) / 10,
    product(T, E, NC, R).

% product_list(L:list, E:number, R:list)
% flow model: product_list(i, i, o)

product_list(L, E, R) :- 
    inv_list(L, LI),
    product(LI, E, 0, RP),
    inv_list(RP, R).

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
% 	maxim_number(l1, maxim_list(l2...ln))
	
% maxim_list(L:list, R:number)
% maxim_list(i, o)

maxim_list([H], H).
maxim_list([H|T], R) :- 
    maxim_list(T, RM),
    maxim_number(H, RM, R).

% Model matematic:
% replace_pos(l1...ln, e, pos) =
% 	[], n = 0
% 	pos + replace_pos(l2...ln, e, pos + 1), l1 = e
%   replace_pos(l2...ln, e, pos + 1), l1 != e	

% replace_pos(L:list, E:number, Pos:number, R:list)
% flow model: replace_pos(i, i, i, o)

replace_pos([], _, _, []).
replace_pos([H|T], E, Pos, [Pos|R]) :- H =:= E,
    NPos is Pos + 1,
    replace_pos(T, E, NPos, R).
replace_pos([H|T], E, Pos, R) :- H =\= E,
    NPos is Pos + 1,
    replace_pos(T, E, NPos, R).

% Model matematic:
% heterList(l1...ln, list) = 
% 	[], n = 0
% 	replace_pos(l1, maxim_list(l1), 1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    maxim_list(H, RM),
    replace_pos(H, RM, 1, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).