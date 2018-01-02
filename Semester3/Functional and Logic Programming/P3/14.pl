% 14. Write a program to generate the list of all subsets of sum S with the elements of a list (S - given).
% Eg: [1,2,3,4,5,6,10] si S=10 => [[1,2,3,4], [1,4,5], [2,3,5], [4,6], [10]] (not necessary in this order)

% subsets(l1...ln) =
% 	[], n = 0
% 	l1 + subsets(l2...ln), n > 0
% 	subsets(l2...ln), n > 0

% subsets(L:list, R:list)
% subsets(i, o)

subsets([], []).
subsets([H|T], [H|R]) :-
    subsets(T, R).
subsets([_|T], R) :-
    subsets(T, R).

% suma(l1...ln, c) =
% 	c, n = 0
% 	suma(l2...ln, c + l1), otherwise

% suma(L:list, C:number, R:number)
% suma(i, i, o)

suma([], C, C).
suma([H|T], C, R) :-
    NC is C + H,
    suma(T, NC, R).

% check(l1...ln, n) =
% 	true, suma(l1...ln, 0) mod n = 0
% 	false, otherwise

% check(L:list, N:number)
% check(i, i)

check(L, S) :-
    suma(L, 0, S).

% onesolution(L:list, N:number, R:list)
% onesolution(i, i, o)

onesolution(L, S, R) :- 
    subsets(L, R),
    suma(R, 0, S).

% allsolutions(L:list, S:number, R:list)
% allsolutions(i, i, o)

allsolutions(L, S, R) :-
    findall(RPartial, onesolution(L, S, RPartial), R).