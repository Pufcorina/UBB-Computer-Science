% 8. Generate all strings of n parenthesis correctly closed.
% Eg: n=4 => (()) and () ()

paranteza('(').
paranteza(')').

% check(l1...ln, c) =
% 	check(l2...ln, c + 1), l1 = '('
% 	check(l2...ln, c - 1), l1 = ')' and c > 0

% check(L:list, C:number)
% check(i, i)

check([], 0).
check([H|T], C) :-
    H == '(',
    NC is C + 1,
    check(T, NC).
check([H|T], C) :-
    H == ')',
    C > 0,
    NC is C - 1,
    check(T, NC).

% allcombinations(n, i, c) =
% 	c, i = n
% 	allcombinations(n, i + 1, paranteza(x) + c), otherwise

% allcombinations(N:number, I:number, C:list, R:list)
% allcombinations(i, i, i, o)

allcombinations(N, N, C, C) :- !.
allcombinations(N, I, C, R) :-
    paranteza(X),
    NI is I + 1,
    allcombinations(N, NI, [X|C], R).

% onesolution(N:number, R:list)
% onesolution(i, o)

onesolution(N, R) :-
    allcombinations(N, 0, [], R),
    check(R, 0).

% allsolutions(N:number, R:list)
% allsolutions(i, o)

allsolutions(N, R) :-
    findall(RPartial, onesolution(N, RPartial), R).