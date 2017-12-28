% Seminar 2 - Prolog

% 1. Multiply elements of a list with a constant value
% L = [2, 3, 4, 1], k = 2   =>  R = [4, 6, 8, 2]

% multiply(l1...ln, k) = 
% 	[], n = 0
% 	k * l1 + multiply(l2...ln, k), otherwise

% multiply(L:list, K: number, R:list)
% multiply(i, i, o)

multiply([], _, []).
multiply([H|T], K, [HN|R]) :-
    HN is H * K,
    multiply(T, K, R).

% 2. Add an element at the end of a list

% append(l1...ln, e) =
% 	[e], n = 0
% 	l1 + append(l2...ln), otherwise

% append(L:list, E:number, R:list)
% append(i, i, o)

append([], E, [E]).
append([H|T], E, [H|R]) :-
    append(T, E, R).

% 3. Compute number of occurrences of an element in a list.

% nOcc(l1...ln, e) =
% 	0, n = 0
% 	1 + nOcc(l2...ln, e), l1 = e
% 	nOcc(l2...ln, e), l1 != e

% nOcc(L:list, E:number, R:number)
% nOcc(i, i, o)

nOcc([], _, 0).
nOcc([H|T], E, R) :- H =:= E,
    nOcc(T, E, RC),
    R is RC + 1.
nOcc([H|T], E, R) :- H =\= E,
    nOcc(T, E, R).

% 4.Eliminate all elements with just one occurrence from a list.

% del_one(l1...ln) =
% 	[], n = 0
% 	l1 + del_one(l2...ln), nOcc(l1...ln, l1) != 1
% 	del_one(l2...ln), nOcc(l1...ln, l1) = 1

% del_one(L:list, LC: list, R:list)
% del_one(i, i, o)

del_one([], _, []).
del_one([H|T], L, [H|R]) :-
    nOcc(L, H, RC),
    RC =\= 1, !,
    del_one(T, L, R).
del_one([_|T], L, R) :-
    del_one(T, L, R).

% del(L:list, R:list)
% del(i, o)
del(L, R) :- del_one(L, L, R).