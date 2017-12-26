% 11.
% a. Write a predicate to substitute an element from a list with another element in the list.
% b. Write a predicate to create the sublist (lm, …, ln) from the list (l1,…, lk).

% a

% Model matematic:
% substitute_elem(l1...ln, e1, e2) =
% 	[], n = 0
% 	e2 + substitute_elem(l2...ln, e1, e2), l1 = e1
% 	l1 + substitute_elem(l2...ln, e1, e2), l1 != e1
	
% substitute_elem(L:list, E1:number, E2:number, R:list)
% substitute_elem(i, i, i, o)

substitute_elem([], _, _, []) :- !.
substitute_elem([H|T], E1, E2, [E2|R]) :- H =:= E1,
    substitute_elem(T, E1, E2, R).
substitute_elem([H|T], E1, E2, [H|R]) :- H =\= E1,
    substitute_elem(T, E1, E2, R).

% b
 
% Model matematic:
% sublist(l1...lk, m, n, pos) =
% 	[], pos > n
% 	l1 + sublist(l2...lk, m, n, pos + 1), m < pos and pos < n
% 	sublist(l2...lk, m, n, pos + 1), otherwise

% sublist(L:list, M:number, N:number, POS:number, R:list)
% sublist(i, i, i, o)

sub_list(_, _, N, POS, []) :- POS > N, !.
sub_list([H|T], M, N, POS, [H|R]) :- M =< POS, POS =< N,
    New_pos is POS + 1,
    sub_list(T, M, N, New_pos, R), !.
sub_list([_|T], M, N, POS, R) :-
    New_pos is POS + 1,
    sub_list(T, M, N, New_pos, R).