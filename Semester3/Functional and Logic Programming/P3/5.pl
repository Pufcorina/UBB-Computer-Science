% 5. Two integers, n and m are given. 
% Write a predicate to determine all possible sequences of numbers from 1 to n, 
% such that between any two numbers from consecutive positions, 
% the absolute difference to be >= m.

% diff(a, b) =
% 	b - a, a < b
% 	a - b, a > b

% diff(A:number, B:number, R:number)
% diff(i, i, o)

diff(A, B, R) :-
    A < B,
    R is B - A.
diff(A, B, R) :-
    A > B,
    R is A - B.

% sequences(n, i) =
% 	[], i = n + 1
% 	i + sequences(n, i + 1), i <= n
% 	sequences(n, i + 1), i <= n

% sequences(N:number, I:number, R:list)
% sequences(i, i, o)

sequences(N, I, []) :- I =:= N + 1, !.
sequences(N, I, [I|R]) :-
    I =< N,
    NI is I + 1,
    sequences(N, NI, R).
sequences(N, I, R) :-
    I =< N,
    NI is I + 1,
    sequences(N, NI, R).

% check(l1...ln, m) =
% 	true, diff(l1, l2) >= m and n = 2
% 	check(l2...ln, m), diff(l1, l2) >= m and n > 2
% 	false, otherwise

% check(L:list, M:mumber)
% check(i, i)

check([H1, H2], M):-
    diff(H1, H2, RD),
    RD >= M, !.
check([H1, H2|T], M) :-
    diff(H1, H2, RD),
    RD >= M,
    check([H2|T], M).

% onesolution(N:number, M:number, R:list)
% onesolution(i, i, o)

onesolution(N, M, R) :-
    sequences(N, 1, R),
    check(R, M).

% allsolutions(N:number, M:number R:list)
% allsolutions(i, i, o)

allsolutions(N, M, R) :-
    findall(RPartial, onesolution(N, M, RPartial), R).