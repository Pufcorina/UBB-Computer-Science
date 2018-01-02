% 10. For a list a1... an with integer and distinct numbers, 
% define a predicate to determine all subsets with sum of elements divisible with n.

% subsets(l1...ln) =
% 	[], n = 0
% 	l1 + subsets(l2...ln), n > 0
% 	subsets(l2...ln), n > 0

% subsets(L:list, R:list)
% subsets(i, o)

subsets([E], [E]).
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

check(L, N) :-
    suma(L, 0, RS),
    RS mod N =:= 0.

% onesolution(L:list, N:number, R:list)
% onesolution(i, i, o)

onesolution(L, N, R) :- 
    subsets(L, R),
    check(R, N).

% allsolutions(L:list, N:number, R:list)
% allsolutions(i, i, o)

allsolutions(L, N, R) :-
    findall(RPartial, onesolution(L, N, RPartial), R).