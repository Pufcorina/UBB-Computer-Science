% 7. A player wants to choose the predictions for 4 games. 
% The predictions can be 1, X, 2. 
% Write a predicate to generate all possible variants considering that: 
% last prediction can’t be 2 and no more than two possible predictions X.

candidat(1).
candidat(3). % X = 3
candidat(2).

% toate(n, c, p) =
% 	c, n = 4
% 	toate(n + 1, candidat(x) + c, p * x), p*x % 27 = 0

% toate(N:number, C:list, R:list, P:number)
% toate(i, i, o, i)

toate(4,R,R,_):-!.
toate(I,C,R,P):-
    candidat(X),
    I1 is I+1,
    P1 is P*X,
    P1 mod 27 =\= 0, 
    toate(I1,[X|C],R,P1).

% rez(R:list)
% rez(o)

rez(R):-toate(1,[1],R,1).
rez(R):-toate(1,[3],R,1).

% allsolutions(R:list)
% allsolutions(o)

allsolutions(R) :-
    findall(RPartial, rez(RPartial), R).