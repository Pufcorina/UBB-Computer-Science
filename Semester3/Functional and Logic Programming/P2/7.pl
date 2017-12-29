% 7.
% a. Determine the position of the maximal element of a linear list.
% Eg.: maxpos([10,14,12,13,14], L) produces L = [2,5].
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% replace every sublist with the position of the maximum element from that sublist.
% [1, [2, 3], [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] =>
% [1, [2], [1, 3], 3, 6, [2], 5, [1, 2, 3], 7]

% a

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

% maxpos(L:list, R:list)
% flow model: maxpos(i, o)
maxpos(H, R) :-
	maxim_list(H, RM),
    replace_pos(H, RM, 1, R).
% b

% Model matematic:
% heterList(l1...ln, list) = 
% 	[], n = 0
% 	replace_pos(l1, maxim_list(l1), 1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    maxpos(H, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).