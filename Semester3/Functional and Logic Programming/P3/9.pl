% 9. Generate all permutation of N (N - given) respecting the property: 
% for every 2<=i<=n exists an 1<=j<=i, so |v(i)-v(j)|=1.

% inserare(l1...ln, e) =
% 	[e], n = 0
% 	e + l1...ln, n >= 1
% 	l1 + inserare(l2...ln, e), otherwise

% inserare(L:list, E:number, R:list)
% inserare(i, i, o)

inserare([], E, [E]).
inserare([H|T], E, [E,H|T]).
inserare([H|T], E, [H|R]) :- 
         inserare(T, E, R).

% permutari(l1...ln) =
% 	[], n = 0
% 	inserare(permutari(l2...ln), l1), otherwise

% permutari(L:list, R:list)
% permutari(i, o)

permutari([], []).
permutari([H|T], R) :-
    permutari(T, RP),
    inserare(RP, H, R).

% createList(n) =
% 	[], n = 0
% 	n + createList(n - 1), n > 0

% createList(N:number, R:list)
% createList(i, o)

createList(0, []).
createList(N, [N|R]) :-
    N > 0,
    NN is N - 1,
    createList(NN, R).

% checkPerm(L:list, E:number)
% checkPerm(i, i)
 	
checkPerm([], _).
checkPerm([H|T], L) :-
    check(L, H),
    checkPerm(T, [H|L]).

% diff(a, b) =
% 	a - b, a > b
% 	b - a, a < b

% diff(A:number, B:number, R:number)
% diff(i, i, o)

diff(A, B, R) :-
    A > B,
    R is A - B.
diff(A, B, R) :-
    A =< B,
    R is B - A.

% check(l1...ln, e) =
% 	true, n = 0
% 	true, diff(l1, e) = 1
% 	check(l2...ln, e), n > 0
% 	false, otherwise

% check(L:list, E:number)
% check(i, i)

check([], _).
check([H|_], X) :-
    diff(X, H, R),
    R =:= 1, !.
check([_|T], X) :-
    check(T, X).

% allsolutions(L:list, R:list)
% allsolutions(i, o)
onesolution(L, R) :-
    permutari(L, R),
    checkPerm(R, []).

% allsolutions(N:number, R:list)
% allsolutions(i, o)

allsolutions(N, R) :-
    createList(N, RL),
    findall(RP, onesolution(RL, RP), R).