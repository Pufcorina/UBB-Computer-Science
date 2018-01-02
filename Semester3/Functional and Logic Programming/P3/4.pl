% 4. The list a1...an is given. Write a predicate to determine all sublists strictly ascending of this list a.

% subset(l1..ln) = 
% 	[], n = 0
% 	l1 + subset(l2..ln)
%	subset(l2..ln)

% subset(L:list, R:list)
% subset(i, o)

subset([], []).
subset([E|T], [E|R]):-
  subset(T, R).
subset([_|T], R):-
  subset(T, R).

% is_sorted(l1..ln) = 
% 	True, n = 0 || n = 1
%	False, l1 > l2
%	is_sorted(l2..ln), l2 >= l1

% ascending(L:list)
% ascending(i)
ascending([]):-!.
ascending([_]):-!.
ascending([H1, H2|T]):-
	H1 =< H2,
	ascending([H2|T]).

%process(+L:list, -R:list)
%process(l1..ln) = 
%	[], n = 0
%	subset(l1..ln), ascending(subset(l1..ln))

process([], []).
process(L, K):-
	subset(L, K),
	ascending(K).

% allsolutions(N:number, R:list)
% allsolutions(i, o)

allsolutions(L, R) :-
    findall(RPartial, process(L, RPartial), R).