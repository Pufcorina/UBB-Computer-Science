% 13.
% a. Given a linear numerical list write a predicate to remove all sequences of consecutive values.
% Eg.: remove([1, 2, 4, 6, 7, 8, 10], L) will produce L=[4, 10].
% b. For a heterogeneous list, formed from integer numbers and list of numbers; 
% write a predicate to delete from every sublist all sequences of consecutive values.
% Eg.: [1, [2, 3, 5], 9, [1, 2, 4, 3, 4, 5, 7, 9], 11, [5, 8, 2], 7] =>
% [1, [5], 9, [4, 7, 9], 11, [5, 8, 2], 7] 

% a

% Model matematic:
% remConsecutive(l1...ln) =
% 	l1...ln, n <= 1
% 	[], n = 2 and l2 = l1 + 1
% 	remConsecutive(l2...ln), l2 = l1 + 1 and l3 = l2 + 1
% 	remConsecutive(l3...ln), l2 = l1 + 1 and l3 != l2 + 1
% 	l1 + remConsecutive(l2...ln), l2 != l1 + 1

% remConsecutive(L:list, R:list)
% flow model: remConsecutive(i, o)

remConsecutive([], []).
remConsecutive([E], [E]).
remConsecutive([H1, H2], []) :- H2 =:= H1 + 1.
remConsecutive([H1, H2], [H1, H2]) :- H2 =\= H1 + 1.
remConsecutive([H1, H2, H3|T], R) :- 
    H2 =:= H1 + 1,
    H3 =:= H2 + 1,
    remConsecutive([H2, H3|T], R).
remConsecutive([H1, H2, H3|T], R) :- 
    H2 =:= H1 + 1,
    H3 =\= H2 + 1,
    remConsecutive([H3|T], R).
remConsecutive([H1, H2, H3|T], [H1|R]) :- 
    H2 =\= H1 + 1,
    remConsecutive([H2,H3|T], R).

% b

% Model matematic:
% heterList(l1...ln) = 
% 	[], n = 0
% 	remConsecutive(l1) + heterList(l2...ln), is_list(l1) = True
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], [HR|R]) :- is_list(H), !,
    remConsecutive(H, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).
    